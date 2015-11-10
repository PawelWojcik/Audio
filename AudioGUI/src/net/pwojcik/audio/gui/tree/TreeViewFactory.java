package net.pwojcik.audio.gui.tree;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.evaluator.TreeContentSelectionChangeEvaluator;
import net.pwojcik.audio.gui.util.ImageProvider;
import net.pwojcik.audio.module.Module;
import net.pwojcik.audio.tree.TreeDescription;
import net.pwojcik.audio.tree.TreeItemValueWrapper;

public final class TreeViewFactory {

	private final Map<String, TreeItem<TreeItemValueWrapper>> nodeMap;
	private final TreeItem<TreeItemValueWrapper> rootItem;
	private final Broadcaster broadcaster;
	public TreeViewFactory(Broadcaster primaryBroadcaster) {
		broadcaster = primaryBroadcaster;
		rootItem = new TreeItem<>();
		nodeMap = new HashMap<>();
	}

	public TreeView<TreeItemValueWrapper> prepareTreeView(Collection<Module> modules) {
		TreeView<TreeItemValueWrapper> treeView = new TreeView<>(rootItem);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().selectedItemProperty().addListener(new TreeContentSelectionChangeEvaluator(broadcaster));
		for (Module module : modules) {
			addTreeNode(module);
		}
		return treeView;
	}

	private void addTreeNode(Module module) {
		Optional<TreeDescription> optionalTreeProvider = module.getTreeDescription();
		if (optionalTreeProvider.isPresent()) {
			TreeDescription treeDescription = optionalTreeProvider.get();
			String moduleType = module.getType();
			TreeItem<TreeItemValueWrapper> treeNode = createTreeNode(treeDescription, moduleType);
			TreeItem<TreeItemValueWrapper> parent = getParentNode(treeDescription);
			addNodeToParent(treeNode, parent);
			nodeMap.put(moduleType, treeNode);
		}
	}

	private TreeItem<TreeItemValueWrapper> createTreeNode(TreeDescription treeDescription, String moduleType) {
		TreeItemValueWrapper treeItemValueWrapper = new TreeItemValueWrapper(treeDescription.getTreeLabel(),
				moduleType);
		Optional<String> treeIconPath = treeDescription.getTreeIconPath();
		TreeItem<TreeItemValueWrapper> treeNode;
		if (treeIconPath.isPresent()) {
			Node icon = ImageProvider.getImageView(treeIconPath.get());
			treeNode = new TreeItem<>(treeItemValueWrapper, icon);
		} else {
			treeNode = new TreeItem<>(treeItemValueWrapper);
		}
		return treeNode;
	}

	private TreeItem<TreeItemValueWrapper> getParentNode(TreeDescription treeDescription) {
		Optional<String> parentModule = treeDescription.getParentModule();
		TreeItem<TreeItemValueWrapper> parent;
		if (parentModule.isPresent() && nodeMap.containsKey(parentModule.get())) {
			parent = nodeMap.get(parentModule.get());
		} else {
			parent = rootItem;
		}
		return parent;
	}

	private void addNodeToParent(TreeItem<TreeItemValueWrapper> treeNode, TreeItem<TreeItemValueWrapper> parent) {
		parent.getChildren().add(treeNode);
		if (!parent.isExpanded()) {
			parent.setExpanded(true);
		}
	}

}
