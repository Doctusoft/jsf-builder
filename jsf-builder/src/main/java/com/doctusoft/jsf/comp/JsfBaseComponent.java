package com.doctusoft.jsf.comp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.doctusoft.bean.binding.ConstantValueBinding;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.jsf.comp.model.HasComponentModel;
import com.doctusoft.jsf.comp.model.JsfBaseComponentModel;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public abstract class JsfBaseComponent<Actual, Model extends JsfBaseComponentModel> implements HasComponentModel {
	
	protected Model model;
	
	protected List<SyleClassRegistration> syleClassRegistrations = null;
	
	public JsfBaseComponent(Model model) {
		this.model = model;
	}

	public Model getModel() {
		return model;
	}
	
	public Actual appendTo(IsContainer<?> container) {
		container.add(this);
		return (Actual) this;
	}
	
	public Actual onclick(String onclick) {
		model.setOnclick(new ConstantValueBinding<String>(onclick));
		return (Actual) this;
	}
	
	public Actual onkeydown(String onkeydown) {
		model.setOnkeydown(new ConstantValueBinding<String>(onkeydown));
		return (Actual) this;
	}

	public Actual onkeypress(String onkeypress) {
		model.setOnkeypress(new ConstantValueBinding<String>(onkeypress));
		return (Actual) this;
	}

	public Actual onkeyup(String onkeyup) {
		model.setOnkeyup(new ConstantValueBinding<String>(onkeyup));
		return (Actual) this;
	}
	
	/**
	 * Can't be used together with bindStyleClassPresent 
	 */
	public Actual styleClass(ValueBinding<String> styleClass) {
		model.setStyleClass(styleClass);
		return (Actual) this;
	}
	
	public Actual style(ValueBinding<String> style) {
		model.setStyle(style);
		return (Actual) this;
	}
	
	/**
	 * Can't be used together with the 'styleClass' builder method 
	 */
	public Actual bindStyleClassPresent(String styleClass, ValueBinding<Boolean> presentBinding) {
		if (syleClassRegistrations == null) {
			syleClassRegistrations = Lists.newArrayList();
			styleClass(new StyleClassBinder());
		}
		syleClassRegistrations.add(new SyleClassRegistration(styleClass, presentBinding));
		return (Actual) this;
	}
	
	@Getter @Setter @AllArgsConstructor
	protected static class SyleClassRegistration {
		private String styleClass;
		private ValueBinding<Boolean> presentBinding;
	}
	
	protected class StyleClassBinder implements ValueBinding<String> {
		@Override
		public String getValue() {
			if (syleClassRegistrations == null) {
				return "";
			}
			List<String> items = Lists.newArrayList();
			for (SyleClassRegistration registration : syleClassRegistrations) {
				if (Objects.firstNonNull(registration.presentBinding.getValue(), true)) {
					items.add(registration.styleClass);
				}
			}
			return Joiner.on(" ").join(items);
		}
		@Override
		public void setValue(String value) {
			// do nothing
		}
	}
}
