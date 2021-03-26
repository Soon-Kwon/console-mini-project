package model;

public class StoreDTO {    
    private int id;
    private int storeId;
    private String storeName; // 사업자 번호 뒷자리 6자리
    private String storeLocation;
    private String phoneNumber;
    private String password;
  
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getStoreId() {
        return storeId;
    }
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getStoreLocation() {
        return storeLocation;
    }
    public void setStoreLocation(String stoerLocation) {
        this.storeLocation = stoerLocation;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
    public boolean equals(Object o) {
        if(o instanceof StoreDTO) {
            StoreDTO store = (StoreDTO) o;
            if(id == store.id) {
                return true;
            }
        }
        return false;
    }
}
