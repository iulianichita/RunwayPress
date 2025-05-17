package model;

public final class Rubric {
    private String title;
    private String article;
    private Magazine publisher;
    private FashionHouse reference;
    private FashionDesigner fashionDesigner;
    private String targetAudience; //ex 18-24

    public Rubric(String title, String article, Magazine publisher, FashionHouse reference, FashionDesigner fashionDesigner, String targetAudience) {
        this.title = title;
        this.article = article;
        this.publisher = publisher; //agregation
        this.reference = reference; //aggregation
        this.fashionDesigner = fashionDesigner; //aggregation
        this.targetAudience = targetAudience;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Magazine getPublisher() {
        return publisher;
    }

    public void setPublisher(Magazine publisher) {
        this.publisher = publisher;
    }

    public FashionHouse getReference() {
        return reference;
    }

    public void setReference(FashionHouse reference) {
        this.reference = reference;
    }

    public FashionDesigner getFashionDesigner() {
        return fashionDesigner;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    @Override
    public String toString() {
        return "\n\nTitle=" + title +
                "\nArticle=" + article +
                "\nPublisher=" + publisher.getName() +
                "\nReference=" + reference.getName() +
                "\nTargetAudience=" + targetAudience;
    }
}
