package com.doctusoft.jsf.render;

import javax.faces.component.html.HtmlPanelGroup;

import com.doctusoft.jsf.comp.model.JsfPanelGroupModel;

public class JsfPanelGroupRenderer extends JsfBaseComponentRenderer<HtmlPanelGroup> {
	
	public JsfPanelGroupRenderer(JsfPanelGroupModel model) {
		super(HtmlPanelGroup.COMPONENT_TYPE, model);
		bind("rendered", model.getRendered(), Boolean.class);
	}

}
