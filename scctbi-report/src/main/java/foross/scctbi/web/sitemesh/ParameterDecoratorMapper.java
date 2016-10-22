package foross.scctbi.web.sitemesh;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;

import javax.servlet.http.HttpServletRequest;

import java.util.Properties;

public class ParameterDecoratorMapper extends AbstractDecoratorMapper {
	private String decoratorParameter = null;
	String nullDecoratorParameter;

	public void init(Config config, Properties properties,
			DecoratorMapper parent) throws InstantiationException {
		super.init(config, properties, parent);
		decoratorParameter = properties.getProperty("decorator.parameter",
				"_layout");
		nullDecoratorParameter = properties.getProperty(
				"nulldecorator.parameter", "null-layout");
	}

	public Decorator getDecorator(HttpServletRequest request, Page page) {
		Decorator result = null;
		String decoratorParamValue = request.getParameter(decoratorParameter);

		if (decoratorParamValue != null
				&& !decoratorParamValue.trim().equals("")) {
			if ("false".equals(decoratorParamValue)) {
				result = getNamedDecorator(request, nullDecoratorParameter);
			} else {
				result = getNamedDecorator(request, decoratorParamValue);
			}

		}
		return result == null ? super.getDecorator(request, page) : result;
	}
}