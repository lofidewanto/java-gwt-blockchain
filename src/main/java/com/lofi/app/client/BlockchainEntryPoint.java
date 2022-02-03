package com.lofi.app.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.jboss.elemento.Elements;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import elemental2.dom.Document;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLLabelElement;

public class BlockchainEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(BlockchainEntryPoint.class.getName());

	private static int prefix = 4;

	private HTMLLabelElement label;

	@Override
	public void onModuleLoad() {
		Element div = DomGlobal.document.getElementById("hello");
		
		HTMLButtonElement button = Elements.button("Click me!").element();
		label = Elements.label("...").element();
		
		div.append(button);
		div.append(label);
		
		button.addEventListener("click", event -> {
			createBlockchain();
		});
	}

	private void createBlockchain() {
		logger.info("Hello Blockchain!");
		label.innerHTML = "Hello Blockchain!";

		List<Block> blockchain = new ArrayList<Block>();
		
		logger.info("Genesis Block");
		label.innerHTML = "Genesis Block";
		Block genesisBlock = new Block("The is the Genesis Block.", "0", new Date().getTime());
		genesisBlock.mineBlock(prefix);
		blockchain.add(genesisBlock);
		
		logger.info("First Block");
		label.innerHTML = "First Block";
		Block firstBlock = new Block("The is the First Block.", genesisBlock.getHash(), new Date().getTime());
		firstBlock.mineBlock(prefix);
		blockchain.add(firstBlock);

		logger.info("New Block");
		label.innerHTML = "New Block";
		Block newBlock = new Block("The is a New Block.", blockchain.get(blockchain.size() - 1).getHash(),
				new Date().getTime());
		newBlock.mineBlock(prefix);
		blockchain.add(newBlock);
		
		logger.info("Bye Blockchain!");
		label.innerHTML = "Bye Blockchain!";
	}
}