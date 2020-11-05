package com.intuit.martech.microurl.util;

import java.util.regex.Pattern;

public class Constants {
	
		public static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

		public static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

		/**
		 * Used in component id references '.'
		 */
		public static final String ID_SEPARATOR = ".";

		/**
		 * Directory separator '/'
		 */
		public static final String DIR_SEPARATOR = "/";

		/**
		 * Equals separator '='
		 */
		public static final String EQUALS_SEPARATOR = "=";

		/**
		 * Plus separator '+'
		 */
		public static final String PLUS_SEPARATOR = "+";

		/**
		 * Plus separator '+'
		 */
		public static final String PERCENT_SEPARATOR = "%";

		/**
		 * Amp separator '&'
		 */
		public static final String AMPERSAND_SEPARATOR = "&";

		/**
		 * Double Quote (escaped)
		 */
		public static final String DOUBLE_QUOTE = "\"";

		/**
		 * An empty string
		 */
		public static final String EMPTY = "";

		/**
		 * UTF-8
		 */
		public static final String URL_DATA_ENCODING = "UTF-8";

		public static final String COMMON_PREFIX = "common:";

		/**
		 * UniqueId header and Log4j parameter name
		 */
		public static final String REQUEST_HEADER_TID = "intuit_tid";
		public static final String LOG4J_TID = "tid";

		/**
		 * Host header and Log4j parameter name
		 */
		public static final String REQUEST_HEADER_HOST = "Host";
		public static final String LOG4J_HOST_ID = "host";

		public static final String AWS_REGION_KEY = "region";

		public static final String ENVIRONMENT_KEY = "env";

	    /**
	     * MXS keynames for get/set value parameters
	     */
	    public static final String MXS_MODEL_NAME_KEY = "modelName";
	    public static final String MXS_MODEL_VERSION_KEY = "modelVersion";
	    public static final String MXS_EVALUATION_KEY = "modelEvaluation";
	    public static final String MXS_DATA_KEY = "mxsData";
	    public static final String IS_MXS_REQUEST_KEY = "isMxsRequest";
	    
	    


	
}
