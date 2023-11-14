package newApiStart;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//This class for serialization and deserialization of request\response body

public class PostRequestBody {
    private String name;
    private String jop;

    public PostRequestBody() {}
    public PostRequestBody(String name , String jop) {
        setName(name);
        setJop(jop);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJop() {
        return jop;
    }

    public void setJop(String jop) {
        this.jop = jop;
    }


}
