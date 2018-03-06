package url;

import java.util.ArrayList;

public class Url {
    private String url;

    public String getUrl() {
        return url;
    }

    private Url(UrlBuilder builder){
        StringBuilder url = new StringBuilder((builder.https) ? "https://" : "http://");
        url.append(builder.domain);
        url.append(builder.resource);
        url.append((builder.params.size()>0) ? "?" : "");
        for(int i=0;i < builder.params.size();i++){
            url.append((i == 0) ? builder.params.get(i) : "&" + builder.params.get(i));
        }
        url.append((builder.isQa) ? (builder.params.size() > 0) ? "&isQa=true" : "?isQa=true" : "");
        this.url = url.toString();
    }

    public static class UrlBuilder{
        private boolean https;
        private String domain = "";
        private String resource = "";
        private boolean isQa;
        private ArrayList<String> params = new ArrayList<String>();

        public UrlBuilder(String domain){
            this.domain = domain;
        }

        public UrlBuilder withHttps(boolean https){
            this.https = https;
            return this;
        }

        public UrlBuilder withResource(String resource){
            this.resource = resource;
            return this;
        }

        public UrlBuilder withParams(String key,String value){
            this.params.add(key+"="+value);
            return this;
        }

        public UrlBuilder isQa(boolean isQa){
            this.isQa = isQa;
            return this;
        }

        public Url build(){
            return new Url(this);
        }
    }
}
