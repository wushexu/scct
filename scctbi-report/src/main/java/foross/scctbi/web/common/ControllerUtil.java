package foross.scctbi.web.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pentaho.reporting.engine.classic.core.ReportDataFactoryException;
import org.pentaho.reporting.engine.classic.core.parameters.DefaultListParameter;
import org.pentaho.reporting.engine.classic.core.parameters.ParameterContext;
import org.pentaho.reporting.engine.classic.core.parameters.ParameterDefinitionEntry;
import org.pentaho.reporting.engine.classic.core.parameters.ParameterValues;
import org.pentaho.reporting.engine.classic.core.parameters.StaticListParameter;

import foross.scctbi.data.vo.FieldParams;
import foross.scctbi.data.vo.SelectVo;

public class ControllerUtil {
	/**
	 * 转化参数
	 * @param params
	 * @param context
	 * @return
	 * @throws ReportDataFactoryException
	 */
	public static List<FieldParams> conversionParamList(ParameterDefinitionEntry[] params,ParameterContext context) throws ReportDataFactoryException{
		List<FieldParams> list = new ArrayList<FieldParams>();
		if(params!=null && params.length>0){
			for(ParameterDefinitionEntry definitionEntry :params){
				FieldParams field = new FieldParams();
				field.setName(definitionEntry.getName());
				field.setLabel(definitionEntry.getParameterAttribute(
				 "http://reporting.pentaho.org/namespaces/engine/parameter-attributes/core",
				 "label", context));
				String renderType = definitionEntry.getParameterAttribute(
						 "http://reporting.pentaho.org/namespaces/engine/parameter-attributes/core",
						 "parameter-render-type", context);
				field.setDefaultValue(definitionEntry.getDefaultValue(context));
				if (definitionEntry instanceof DefaultListParameter) {//列表查询
					DefaultListParameter  parameter = (DefaultListParameter)definitionEntry;
					ParameterValues parameterValues = parameter.getValues(context);
					if(null!=parameterValues && parameterValues.getRowCount()>0){
						List<SelectVo> selects = new ArrayList<SelectVo>();
						for(int i = 0 ;i<parameterValues.getRowCount();i++){
							selects.add(new SelectVo(parameterValues.getKeyValue(i),parameterValues.getTextValue(i)));
						}
						field.setSelects(selects);
					}
				}else if(definitionEntry instanceof StaticListParameter){//静态列表
					StaticListParameter  parameter = (StaticListParameter)definitionEntry;
					ParameterValues parameterValues = parameter.getValues(context);
					if(null!=parameterValues && parameterValues.getRowCount()>0){
						List<SelectVo> selects = new ArrayList<SelectVo>();
						for(int i = 0 ;i<parameterValues.getRowCount();i++){
							selects.add(new SelectVo(parameterValues.getKeyValue(i),parameterValues.getTextValue(i)));
						}
						field.setSelects(selects);
					}
				}
				Boolean isHidden = new Boolean(definitionEntry.getParameterAttribute(
						 "http://reporting.pentaho.org/namespaces/engine/parameter-attributes/core",
						 "hidden", context));
				field.setHidden(isHidden);
				field.setValidate(definitionEntry.isMandatory());
				field.setType(definitionEntry.getValueType().getSimpleName());
				field.setDefaultValue(definitionEntry.getDefaultValue(context));
				field.setRenderType(renderType);
				list.add(field);
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 根据不同的类型生成不同的时间
	 * @param value
	 * @param clz
	 * @return
	 * @throws ParseException
	 */
	public static Object createByclz(String value,Class<?> cls,String format) throws ParseException{
		if(cls == java.lang.String.class){
			return value;
		}
		if(cls == java.lang.Double.class){
			return new java.lang.Double(value);
		}
		if(cls == java.lang.Integer.class){
			return new java.lang.Integer(value);
		}
		if(cls == java.lang.Long.class){
			return new java.lang.Long(value);
		}
		if(cls == java.lang.Boolean.class){
			return new java.lang.Boolean(value);
		}
		if(cls == java.lang.Byte.class){
			return new java.lang.Byte(value);
		}
		if(cls == java.lang.Float.class){
			return new java.lang.Float(value);
		}
		if(cls == java.lang.Short.class){
			return new java.lang.Short(value);
		}
		if(cls == java.util.Date.class && !"".equals(format)){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if(format!=null && !"".equals(format)){
				dateFormat = new SimpleDateFormat(format);
			}
			return dateFormat.parse(value);
		}
		if(cls == java.sql.Date.class){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if(format!=null && !"".equals(format)){
				dateFormat = new SimpleDateFormat(format);
			}
			Date date = dateFormat.parse(value);
			return new java.sql.Date(date.getTime());
		}
		if(cls == java.lang.Number.class){
			return new java.lang.Integer(value);
		}
		if(cls ==java.sql.Time.class){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if(format!=null && !"".equals(format)){
				dateFormat = new SimpleDateFormat(format);
			}
			Date date = dateFormat.parse(value);
			return new java.sql.Time(date.getTime());
		}
		if(cls ==java.math.BigDecimal.class){
			return new java.math.BigDecimal(value);
		}
		if(cls ==java.math.BigInteger.class){
			return new java.math.BigInteger(value);
		}
		return new Object();
	}
	/**
	 * 根据不同的类别生成不同的数据
	 * @param cls
	 * @return
	 */
	public static Object newInstance(Class<?> cls){
		if(cls == java.lang.String.class){
			return new java.lang.String();
		}
		if(cls == java.lang.Double.class){
			return new java.lang.Double(0);
		}
		if(cls == java.lang.Integer.class){
			return new java.lang.Integer(0);
		}
		if(cls == java.lang.Long.class){
			return new java.lang.Long(0);
		}
		if(cls == java.lang.Boolean.class){
			return new java.lang.Boolean(false);
		}
		if(cls == java.lang.Byte.class){
			return new java.lang.Byte("0");
		}
		if(cls == java.lang.Float.class){
			return new java.lang.Float("0");
		}
		if(cls == java.lang.Short.class){
			return new java.lang.Short("0");
		}
		if(cls == java.util.Date.class){
			return null;
		}
		if(cls == java.sql.Date.class){
			return null;
		}
		if(cls == java.lang.Number.class){
			return new java.lang.Integer(0);
		}
		if(cls ==java.sql.Time.class){
			return null;
		}
		if(cls ==java.math.BigDecimal.class){
			return new java.math.BigDecimal(0);
		}
		if(cls ==java.math.BigInteger.class){
			return new java.math.BigInteger("0");
		}
		return new Object();
	}
}
