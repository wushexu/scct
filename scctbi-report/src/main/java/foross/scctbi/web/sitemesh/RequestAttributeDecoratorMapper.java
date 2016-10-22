package foross.scctbi.web.sitemesh;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;

public class RequestAttributeDecoratorMapper extends AbstractDecoratorMapper {
	private final Logger logger = Logger
			.getLogger(RequestAttributeDecoratorMapper.class);

	String decoratorAttributeName;
	String nullDecoratorAttributeName;

	public void init(Config config, Properties properties,
			DecoratorMapper parent) throws InstantiationException {
		super.init(config, properties, parent);

		decoratorAttributeName = properties.getProperty("decorator.attribute",
				"_layout");
		nullDecoratorAttributeName = properties.getProperty(
				"nulldecorator.attribute", "null-layout");
	}

	public Decorator getDecorator(HttpServletRequest request, Page page) {
		try {
			Decorator result = null;

			Object decorator = request.getAttribute(decoratorAttributeName);
			if (decorator instanceof String) {
				String decoratorName = (String) decorator;
				if (decoratorName != null) {
					if ("false".equals(decoratorName)) {
						result = getNamedDecorator(request,
								nullDecoratorAttributeName);
					} else {
						result = getNamedDecorator(request, decoratorName);
					}
				}
			} else if (decorator instanceof Boolean) {
				boolean useDecorator = (Boolean) decorator;
				{
					if (!useDecorator) {
						result = getNamedDecorator(request,
								nullDecoratorAttributeName);
					}
				}
			}

			return result == null ? super.getDecorator(request, page) : result;
		} catch (NullPointerException e) {
			return super.getDecorator(request, page);
		}
	}
}
