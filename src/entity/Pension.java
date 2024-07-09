package entity;

public class Pension {
    // Pansiyon özelliklerini temsil eden değişkenler
    private int pension_type_id;
    private int pension_type_hotel_id;
    private boolean pension_type_ultra;
    private boolean pension_type_hsd;
    private boolean pension_type_breakfast;
    private boolean pension_type_tam;
    private boolean pension_type_yarim;
    private boolean pension_type_just_bed;
    private boolean pension_type_ahfc;

    // Boş constructor
    public Pension() {
    }

    // Pansiyon ID'si ile başlatan constructor
    public Pension(int pension_type_id) {
        this.pension_type_id = pension_type_id;
    }

    // Pansiyon türü ID'sini döndürür
    public Integer getPension_type_id() {
        return pension_type_id;
    }

    // Pansiyon türü ID'sini ayarlar
    public void setPension_type_id(int pension_type_id) {
        this.pension_type_id = pension_type_id;
    }

    // Pansiyon türünün bağlı olduğu otelin ID'sini döndürür
    public int getPension_type_hotel_id() {
        return pension_type_hotel_id;
    }

    // Pansiyon türünün bağlı olduğu otelin ID'sini ayarlar
    public void setPension_type_hotel_id(int pension_type_hotel_id) {
        this.pension_type_hotel_id = pension_type_hotel_id;
    }

    // Ultra her şey dahil olup olmadığını döndürür
    public boolean isPension_type_ultra() {
        return pension_type_ultra;
    }

    // Ultra her şey dahil olup olmadığını ayarlar
    public void setPension_type_ultra(boolean pension_type_ultra) {
        this.pension_type_ultra = pension_type_ultra;
    }

    // Her şey dahil olup olmadığını döndürür
    public boolean isPension_type_hsd() {
        return pension_type_hsd;
    }

    // Her şey dahil olup olmadığını ayarlar
    public void setPension_type_hsd(boolean pension_type_hsd) {
        this.pension_type_hsd = pension_type_hsd;
    }

    // Oda kahvaltı olup olmadığını döndürür
    public boolean isPension_type_breakfast() {
        return pension_type_breakfast;
    }

    // Oda kahvaltı olup olmadığını ayarlar
    public void setPension_type_breakfast(boolean pension_type_breakfast) {
        this.pension_type_breakfast = pension_type_breakfast;
    }

    // Tam pansiyon olup olmadığını döndürür
    public boolean isPension_type_tam() {
        return pension_type_tam;
    }

    // Tam pansiyon olup olmadığını ayarlar
    public void setPension_type_tam(boolean pension_type_tam) {
        this.pension_type_tam = pension_type_tam;
    }

    // Yarım pansiyon olup olmadığını döndürür
    public boolean isPension_type_yarim() {
        return pension_type_yarim;
    }

    // Yarım pansiyon olup olmadığını ayarlar
    public void setPension_type_yarim(boolean pension_type_yarim) {
        this.pension_type_yarim = pension_type_yarim;
    }

    // Sadece yatak olup olmadığını döndürür
    public boolean isPension_type_just_bed() {
        return pension_type_just_bed;
    }

    // Sadece yatak olup olmadığını ayarlar
    public void setPension_type_just_bed(boolean pension_type_just_bed) {
        this.pension_type_just_bed = pension_type_just_bed;
    }

    // Alkol hariç full credit olup olmadığını döndürür
    public boolean isPension_type_ahfc() {
        return pension_type_ahfc;
    }

    // Alkol hariç full credit olup olmadığını ayarlar
    public void setPension_type_ahfc(boolean pension_type_ahfc) {
        this.pension_type_ahfc = pension_type_ahfc;
    }

    // Pansiyon özelliklerini bir String olarak döndürür
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (pension_type_ultra) sb.append("Ultra Her şey Dahil, ");
        if (pension_type_hsd) sb.append("Her şey Dahil, ");
        if (pension_type_breakfast) sb.append("Oda Kahvaltı, ");
        if (pension_type_tam) sb.append("Tam Pansiyon, ");
        if (pension_type_yarim) sb.append("Yarım Pansiyon, ");
        if (pension_type_just_bed) sb.append("Sadece Yatak, ");
        if (pension_type_ahfc) sb.append("Alkol Hariç Full Credit , ");

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Sondaki virgül ve boşluğu kaldırır
        }

        return sb.toString();
    }
}