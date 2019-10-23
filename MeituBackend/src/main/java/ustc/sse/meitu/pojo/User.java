package ustc.sse.meitu.pojo;

public class User {
    private Integer uid;

    private String uavator;

    private String uname;

    private String uintro;

    private String ulocation;

    private String uusername;

    private String upassword;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUavator() {
        return uavator;
    }

    public void setUavator(String uavator) {
        this.uavator = uavator == null ? null : uavator.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUintro() {
        return uintro;
    }

    public void setUintro(String uintro) {
        this.uintro = uintro == null ? null : uintro.trim();
    }

    public String getUlocation() {
        return ulocation;
    }

    public void setUlocation(String ulocation) {
        this.ulocation = ulocation == null ? null : ulocation.trim();
    }

    public String getUusername() {
        return uusername;
    }

    public void setUusername(String uusername) {
        this.uusername = uusername == null ? null : uusername.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }
}