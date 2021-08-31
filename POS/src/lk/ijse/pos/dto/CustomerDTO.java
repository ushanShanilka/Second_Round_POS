package lk.ijse.pos.dto;

public class CustomerDTO {
    private String  cID;
    private String name;
    private String address;

    public CustomerDTO ( ) {
    }

    public CustomerDTO ( String cID , String name , String address ) {
        this.cID = cID;
        this.name = name;
        this.address = address;
    }

    public String getcID ( ) {
        return cID;
    }

    public void setcID ( String cID ) {
        this.cID = cID;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getAddress ( ) {
        return address;
    }

    public void setAddress ( String address ) {
        this.address = address;
    }

    @Override
    public String toString ( ) {
        return "CustomerDTO{" +
               "cID='" + cID + '\'' +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}
