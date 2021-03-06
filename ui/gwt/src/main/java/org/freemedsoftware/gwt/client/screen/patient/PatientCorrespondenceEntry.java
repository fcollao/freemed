/*
 * $Id$
 *
 * Authors:
 *      Jeff Buchbinder <jeff@freemedsoftware.org>
 *
 * FreeMED Electronic Medical Record and Practice Management System
 * Copyright (C) 1999-2012 FreeMED Software Foundation
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package org.freemedsoftware.gwt.client.screen.patient;

import static org.freemedsoftware.gwt.client.i18n.I18nUtil._;

import org.freemedsoftware.gwt.client.PatientEntryScreenInterface;
import org.freemedsoftware.gwt.client.Util;
import org.freemedsoftware.gwt.client.i18n.AppConstants;
import org.freemedsoftware.gwt.client.widget.CustomButton;
import org.freemedsoftware.gwt.client.widget.CustomDatePicker;
import org.freemedsoftware.gwt.client.widget.CustomRichTextArea;
import org.freemedsoftware.gwt.client.widget.CustomTextBox;
import org.freemedsoftware.gwt.client.widget.SupportModuleWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PatientCorrespondenceEntry extends PatientEntryScreenInterface {

	protected CustomDatePicker wDate;

	protected SupportModuleWidget wFrom, wTo;

	protected CustomTextBox wSubject;

	protected CustomRichTextArea wText;

	final protected String moduleName = "PatientCorrespondence";

	public PatientCorrespondenceEntry() {
		this.patientIdName = "letterpatient";

		final VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);

		final FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);

		final Label dateLabel = new Label(_("Date") + " : ");
		flexTable.setWidget(0, 0, dateLabel);

		wDate = new CustomDatePicker();
		wDate.setHashMapping("letterdt");
		addEntryWidget("letterdt", wDate);
		flexTable.setWidget(0, 1, wDate);

		final Label fromLabel = new Label(_("From") + " : ");
		flexTable.setWidget(1, 0, fromLabel);

		wFrom = new SupportModuleWidget();
		wFrom.setModuleName("ProviderModule");
		wFrom.setHashMapping("letterfrom");
		addEntryWidget("letterfrom", wFrom);
		flexTable.setWidget(1, 1, wFrom);

		final Label subjectLabel = new Label(_("Subject") + " : ");
		flexTable.setWidget(2, 0, subjectLabel);

		wSubject = new CustomTextBox();
		wSubject.setHashMapping("lettersubject");
		addEntryWidget("lettersubject", wSubject);
		flexTable.setWidget(2, 1, wSubject);
		wSubject.setWidth("100%");

		final Label templateLabel = new Label(_("Template") + " : ");
		flexTable.setWidget(3, 0, templateLabel);

		final Label messageLabel = new Label("Message : ");
		flexTable.setWidget(4, 0, messageLabel);

		wText = new CustomRichTextArea();
		wText.setHashMapping("lettertext");
		addEntryWidget("lettertext", wText);
		flexTable.setWidget(4, 1, wText);

		final HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		final CustomButton wSubmit = new CustomButton(_("Submit"),
				AppConstants.ICON_ADD);
		buttonBar.add(wSubmit);
		wSubmit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent w) {
				submitForm();
			}
		});
		final CustomButton wReset = new CustomButton(_("Reset"),
				AppConstants.ICON_CLEAR);
		buttonBar.add(wReset);
		wReset.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent w) {
				resetForm();
			}
		});
		verticalPanel.add(buttonBar);
		Util.setFocus(wFrom);
	}

	public String getModuleName() {
		return "PatientCorrespondence";
	}

	public void resetForm() {

	}

}
