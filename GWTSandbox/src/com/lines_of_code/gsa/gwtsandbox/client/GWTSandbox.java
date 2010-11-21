package com.lines_of_code.gsa.gwtsandbox.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTSandbox implements EntryPoint {

	final String[] allowedExt = new String[] { "xls", "xlsx", "xml", "csv",
			"txt" };

	/**
	 * A simple data type that represents a contact.
	 */
	private static class Catalog {
		private final String shortDesc;
		private final String category;
		private final double price;
		private final String aid;

		public Catalog(String shortDesc, String category, double price,
				String aid) {
			super();
			this.shortDesc = shortDesc;
			this.category = category;
			this.price = price;
			this.aid = aid;
		}

	}

	/**
	 * The list of data to display.
	 */
	private static final List<Catalog> CATALOG = Arrays
			.asList(new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
					"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815"),
					new Catalog("Foo Bar", "foobar", Random.nextDouble() * 100,
							"0815")
	);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		TabLayoutPanel tabLayout = new TabLayoutPanel(1.5, Unit.EM);
		tabLayout.setHeight("600px");
		VerticalPanel vPanel = new VerticalPanel();
		VerticalPanel vPanel2 = new VerticalPanel();

		// Center everything in the panel.
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		// Add a FormPanel widget.
		final FormPanel form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL() + "catalogupload");
		form.setMethod(FormPanel.METHOD_POST);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		final FileUpload upload = new FileUpload();

		// Add a form handler.
		form.setWidget(vPanel);

		// add a button to upload the file.
		final Button validateButton = new Button("Katalog validieren");
		validateButton.setEnabled(false);
		validateButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.submit();
				GWT.log("Uploading file: '" + upload.getFilename() + "' to: "
						+ form.getAction());
			}
		});

		// Add file recognition.
		upload.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				boolean fileExtValid = false;
				String fileName = upload.getFilename();
				for (String ext : allowedExt) {
					if (fileName.toLowerCase().endsWith(ext)) {
						fileExtValid = true;
					}
				}
				if (fileExtValid) {
					if (fileName.endsWith("xml")) {
						validateButton.setText("BMEcat validieren");
					} else if (fileName.endsWith("xls")
							|| fileName.endsWith("xlsx")) {
						validateButton.setText("Excel Katalog validieren");
					} else if (fileName.endsWith("txt")
							|| fileName.endsWith("csv")) {
						validateButton.setText("CSV Katalog validieren");
					}
					validateButton.setEnabled(true);
					Window.alert("Datei okay!");
				} else {
					Window.alert("Datei nicht okay!");
				}
			}

		});

		vPanel.add(upload);
		vPanel.add(validateButton);


		// Create a CellTable.
		CellTable<Catalog> table = new CellTable<Catalog>();
		table.setPageSize(10);
		table.setPageStart(0);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the shortDesc.
		TextColumn<Catalog> shortDescColumn = new TextColumn<Catalog>() {
			@Override
			public String getValue(Catalog object) {
				return object.shortDesc;
			}
		};
		table.addColumn(shortDescColumn, "Kurzbezeichnung");

		TextColumn<Catalog> categoryColumn = new TextColumn<Catalog>() {
			@Override
			public String getValue(Catalog object) {
				return object.category;
			}
		};
		table.addColumn(shortDescColumn, "Kategorie");

		// Add a column to show the price.
		TextColumn<Catalog> priceColumn = new TextColumn<Catalog>() {
			@Override
			public String getValue(Catalog object) {
				return String.valueOf(object.price);
			}
		};
		table.addColumn(priceColumn, "Preis");

		// Add a text column to show the address.
		TextColumn<Catalog> aidColumn = new TextColumn<Catalog>() {
			@Override
			public String getValue(Catalog object) {
				return object.aid;
			}
		};
		table.addColumn(aidColumn, "AID");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Catalog> selectionModel = new SingleSelectionModel<Catalog>();
		table.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Catalog selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert("Artikel: " + selected.shortDesc);
						}
					}
				});

		// Set the total row count. This isn't strictly necessary, but it
		// affects
		// paging calculations, so its good habit to keep the row count up to
		// date.
		table.setRowCount(CATALOG.size(), true);

		// Push the data into the widget.
		table.setRowData(0, CATALOG);


		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER,
				pagerResources, false, 00, false);
		pager.setDisplay(table);

		
		vPanel2.add(table);
		vPanel2.add(pager);

		tabLayout.add(vPanel, "Katalogimport");
		tabLayout.add(vPanel2, "Katalog bearbeiten");
		RootPanel.get("tabLayout").add(tabLayout);
	}
}
