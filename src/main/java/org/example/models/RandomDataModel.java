// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package org.example.models;


// data model of the json format from the API
public class RandomDataModel {
    private String duration;
    private String activity;
    private String accessibility;
    private double price;
    private boolean kidFriendly;
    private String link;
    private double availability;
    private String type;
    private String key;
    private long participants;

    public RandomDataModel() {
    }


    public String getDuration() { return duration; }
    public void setDuration(String value) { this.duration = value; }

    public String getActivity() { return activity; }
    public void setActivity(String value) { this.activity = value; }

    public String getAccessibility() { return accessibility; }
    public void setAccessibility(String value) { this.accessibility = value; }

    public double getPrice() { return price; }
    public void setPrice(double value) { this.price = value; }

    public boolean getKidFriendly() { return kidFriendly; }
    public void setKidFriendly(boolean value) { this.kidFriendly = value; }

    public String getLink() { return link; }
    public void setLink(String value) { this.link = value; }

    public double getAvailability() { return availability; }
    public void setAvailability(double value) { this.availability = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getKey() { return key; }
    public void setKey(String value) { this.key = value; }

    public long getParticipants() { return participants; }
    public void setParticipants(long value) { this.participants = value; }

    @Override
    public String toString() {
        return "RandomDataModel{" +
                "duration='" + duration + '\'' +
                ", activity='" + activity + '\'' +
                ", accessibility='" + accessibility + '\'' +
                ", price=" + price +
                ", kidFriendly=" + kidFriendly +
                ", link='" + link + '\'' +
                ", availability=" + availability +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", participants=" + participants +
                '}';
    }
}
