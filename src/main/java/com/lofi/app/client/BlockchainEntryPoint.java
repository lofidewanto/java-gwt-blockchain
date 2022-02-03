package com.lofi.app.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.jboss.elemento.Elements;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Timer;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLLabelElement;

public class BlockchainEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(BlockchainEntryPoint.class.getName());

	private static int prefix = 4;

	private String state = "START";

	private HTMLButtonElement button;

	private HTMLLabelElement label;

	private int index = 0;

	@Override
	public void onModuleLoad() {
		createTimer();

		Element div = DomGlobal.document.getElementById("hello");

		button = Elements.button("Click me!").style("font-family: sans-serif;").element();
		label = Elements.label("Before clicked...").style("margin-left: 10px;font-family: sans-serif;font-size: small;")
				.element();

		div.append(button);
		div.append(label);

		button.addEventListener("click", event -> {
			button.innerHTML = "Creating...";
			
			createBlockchainWithScheduler();

			label.innerHTML = "After clicked...";
		});
	}

	private void createBlockchainWithScheduler() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				createBlockchain();
				
				label.innerHTML = "Bye Blockchain!";
				button.innerHTML = "Click me!";
			}
		});
	}

	private void createTimer() {
		Timer timer = new Timer() {
			@Override
			public void run() {
				logger.info("Timer: " + index);
				index++;

				if (state.equals("GENESIS")) {
					label.innerHTML = "Genesis Block";
				}

				if (state.equals("FIRST")) {
					label.innerHTML = "First Block";
				}
			}
		};

		timer.scheduleRepeating(5000);
	}

	private void createBlockchain() {
		logger.info("Hello Blockchain!");

		List<Block> blockchain = new ArrayList<Block>();

		logger.info("Genesis Block");
		state = "GENESIS";
		Block genesisBlock = new Block("The is the Genesis Block.", "0", new Date().getTime());
		genesisBlock.mineBlock(prefix);
		blockchain.add(genesisBlock);

		logger.info("First Block");
		state = "FIRST";
		Block firstBlock = new Block("The is the First Block.", genesisBlock.getHash(), new Date().getTime());
		firstBlock.mineBlock(prefix);
		blockchain.add(firstBlock);

		logger.info("New Block");
		state = "NEW";
		Block newBlock = new Block("The is a New Block.", blockchain.get(blockchain.size() - 1).getHash(),
				new Date().getTime());
		newBlock.mineBlock(prefix);
		blockchain.add(newBlock);

		logger.info("Bye Blockchain!");
		state = "END";
	}
}