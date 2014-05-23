package com.doctusoft.jsf.example.util;

import java.util.List;

import com.doctusoft.jsf.comp.JsfSelectItem;
import com.google.common.collect.Lists;

public class SelectUtils {
	
	public static List<JsfSelectItem<String>> stringSelectItems(String [] values) {
		List<JsfSelectItem<String>> items = Lists.newArrayList();
		for (String string : values) {
			JsfSelectItem<String> item = new JsfSelectItem<String>();
			item.setLabel(string);
			item.setValue(string);
			item.setId(string);
			items.add(item);
		}
		return items;
		
	}

	public static <EnumType extends Enum<EnumType>> List<JsfSelectItem<EnumType>> enumSelectItems(EnumType [] values) {
		List<JsfSelectItem<EnumType>> items = Lists.newArrayList();
		for (EnumType value : values) {
			JsfSelectItem<EnumType> item = new JsfSelectItem<EnumType>();
			String name = value.name();
			item.setLabel(name);
			item.setValue(value);
			item.setId(name);
			items.add(item);
		}
		return items;
	}
}