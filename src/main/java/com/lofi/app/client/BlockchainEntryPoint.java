package com.lofi.app.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class BlockchainEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(BlockchainEntryPoint.class.getName());

	private static int prefix = 4;

	@Override
	public void onModuleLoad() {
		Button button = new Button("Click me");
		button.addClickHandler(clickEvent -> {
			logger.info("Hello BlockChain!");

			List<Block> blockchain = new ArrayList<Block>();
			Block genesisBlock = new Block("The is the Genesis Block.", "0", new Date().getTime());
			genesisBlock.mineBlock(prefix);
			blockchain.add(genesisBlock);
			Block firstBlock = new Block("The is the First Block.", genesisBlock.getHash(), new Date().getTime());
			firstBlock.mineBlock(prefix);
			blockchain.add(firstBlock);

			Block newBlock = new Block("The is a New Block.", blockchain.get(blockchain.size() - 1).getHash(),
					new Date().getTime());
			newBlock.mineBlock(prefix);
			blockchain.add(newBlock);
		});

		RootPanel.get("helloButton").add(button);
	}
}