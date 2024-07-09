package core;

public class BoxItem {
    // Bu değişkenler, anahtar ve değer için
    private int key;
    private String value;

    // Constructor, bir ComboItem nesnesi oluşturur ve anahtar ile değeri ayarlar
    public BoxItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    // Anahtar değişkenini geri döndürür
    public int getKey() {
        return key;
    }

    // Anahtar değişkenini ayarlamak için
    public void setKey(int key) {
        this.key = key;
    }

    // Değer değişkenini geri döndürür
    public String getValue() {
        return value;
    }

    // Değer değişkenini ayarlamak için
    public void setValue(String value) {
        this.value = value;
    }

    // ComboBox'da neyin görüneceğini belirler
    public String toString() {
        return this.value;
    }
}
