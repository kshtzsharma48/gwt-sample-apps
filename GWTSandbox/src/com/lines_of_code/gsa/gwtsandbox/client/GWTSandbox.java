package com.lines_of_code.gsa.gwtsandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTSandbox implements EntryPoint {

	final String[] allowedExt = new String[] { "xls", "xlsx", "xml", "csv",
			"txt" };
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		TabLayoutPanel tabLayout = new TabLayoutPanel(1.5, Unit.EM);
		VerticalPanel vPanel = new VerticalPanel();

		// Add a FormPanel widget.
		final FormPanel form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL()+"catalogupload");
		form.setMethod(FormPanel.METHOD_POST);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);

		// Add a form handler.

		form.setWidget(vPanel);

		// Add a FileUpload widget.
		final FileUpload upload = new FileUpload();
		upload.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				boolean fileExtValid = false;
				for (String ext : allowedExt) {
					if (upload.getFilename().endsWith(ext)) {
						fileExtValid = true;
					}
				}
				if (fileExtValid) {
					Window.alert("Datei okay!");
				} else {
					Window.alert("Datei nicht okay!");
				}
			}

		});
		
		// add a button to upload the file.
		Button validateButton = new Button("Katalog validieren");
		validateButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
				GWT.log("Uploading file: '" + upload.getFilename() + "' to: "
						+ form.getAction());
			}
		});


		vPanel.add(upload);
		vPanel.add(validateButton);

		tabLayout.add(vPanel, "Katalogimport");
		tabLayout.add(new HTML("blubb"), "Katalog bearbeiten");
		RootPanel.get("tabLayout").add(tabLayout);
	}
}
