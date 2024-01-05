package com.fullstackApp.fullStackApp.ManageClientUser;

public class Project {
    private final int id;
    private final byte[] image;
    private final String name;
    private final String type;
    private final String gitHub;
    private final String testLink;
    private final String description;
    private final String projectId;
    private final byte[] image1;
    private final byte[] image2;
    private final byte[] image3;
    String base64Image;

    public Project(int id, byte[] image, String name, String type, String gitHub, String testLink, String description, String projectId, byte[] image1, byte[] image2, byte[] image3) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.type = type;
        this.gitHub = gitHub;
        this.testLink = testLink;
        this.description = description;
        this.projectId = projectId;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

//    public Project(int id, byte[] images, String name, String type, String gitHub, String testLink, String description, String projectId, byte[] image1s, byte[] image2s, byte[] image3s) {
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getGitHub() {
        return gitHub;
    }

    public String getTestLink() {
        return testLink;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectId() {
        return projectId;
    }

    public byte[] getImage() {
        return image;
    }

    public byte[] getImage1() {
        return image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", gitHub='" + gitHub + '\'' +
                ", testLink='" + testLink + '\'' +
                ", description='" + description + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
