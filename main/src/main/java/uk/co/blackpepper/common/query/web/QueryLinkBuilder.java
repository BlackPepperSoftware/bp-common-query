package uk.co.blackpepper.common.query.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

import uk.co.blackpepper.common.query.PagedQuery;
import uk.co.blackpepper.common.query.SearchQuery;

public final class QueryLinkBuilder {
	
	private static final String START_PARAM = "start";

	private QueryLinkBuilder() {
		throw new AssertionError();
	}

	public static String getLink(PagedQuery query) {
		return toUrl(getParamModel(query));
	}

	public static String getPreviousLink(PagedQuery query) {
		Map<String, Object> paramModel = getParamModel(query);
		paramModel.put(START_PARAM, query.getPreviousPageStart());
		
		return toUrl(paramModel);
	}
	
	public static String getNextLink(PagedQuery query) {
		Map<String, Object> paramModel = getParamModel(query);
		paramModel.put(START_PARAM, query.getNextPageStart());
		
		return toUrl(paramModel);
	}

	private static Map<String, Object> getParamModel(PagedQuery query) {
		Map<String, Object> paramModel = new LinkedHashMap<>();
		paramModel.put(START_PARAM, query.getStart());
		paramModel.put("rows", query.getRows());

		if (query.getOrderBy() != null) {
			paramModel.put("orderBy", query.getOrderBy());
			paramModel.put("order", query.getOrder());
		}
		
		if (query instanceof SearchQuery) {
			String queryString = ((SearchQuery) query).getQueryString();
			if (!queryString.isEmpty()) {
				paramModel.put("queryString", queryString);
			}
		}
		
		return paramModel;
	}
	
	private static String toUrl(Map<String, Object> paramModel) {
		return "?" + FluentIterable.from(paramModel.entrySet())
			.transform(new Function<Entry<String, Object>, String>() {
				@Override
				public String apply(Entry<String, Object> entry) {
					return paramToString(entry.getKey(), entry.getValue());
				}
			})
			.join(Joiner.on("&"));
	}
	
	private static String paramToString(String name, Object value) {
		return String.format("%s=%s", name, encode(value));
	}

	private static String encode(Object value) {
		try {
			return URLEncoder.encode(String.valueOf(value), "UTF-8");
		}
		catch (UnsupportedEncodingException exception) {
			// All JVMs support UTF-8
			throw new AssertionError();
		}
	}
}
