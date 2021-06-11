public class Address {
    private String street;
    private String city;
    private Integer zipCOde;

    public Address(String street, String city, Integer zipCOde) {
        this.street = street;
        this.city = city;
        this.zipCOde = zipCOde;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCOde() {
        return zipCOde;
    }

    public void setZipCOde(Integer zipCOde) {
        this.zipCOde = zipCOde;
    }
}
