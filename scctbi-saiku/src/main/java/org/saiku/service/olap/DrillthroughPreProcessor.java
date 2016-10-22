package org.saiku.service.olap;

public interface DrillthroughPreProcessor {

	String[] process(String mdx, String returns);
}
