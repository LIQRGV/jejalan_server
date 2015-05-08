package co.id.jejalan.test;

public class RestClient {/*
    protected Client client;
    protected ObjectMapper mapper = new ObjectMapper();
    protected WebResource webResource;
    public RestClient() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
        webResource = client.resource("http://localhost:8080/co.id.jejalan/rest/");
    }

    public ClientResponse createBaseBean (BaseBean cluster) {
        ClientResponse cr = webResource.path("ftocservice")
        		.path("header")
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, cluster);
        return cr;
    }
    public void run() {
        
        BaseBean newBaseBean = new BaseBean();
        newBaseBean.setId(1);
        ClientResponse cr = createBaseBean(newBaseBean);
        int status = cr.getStatus();
        if ((status >= 200) && (status <= 299)) {
            System.out.println("BaseBean created.");
        } else {
            System.out.println("BaseBean creation failed: " + cr.getEntity(String.class));
        }
    }
    public static void main(String... args) throws IOException {
        RestClient rc = new RestClient();
        rc.run();
    }*/
}
