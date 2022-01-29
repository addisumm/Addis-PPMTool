package io.agileintelligence.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Backlog {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSeqquence =0;
    private String projectIdentifier;
    //OneToOne with project
    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;
    //OneToMany projecttasks



    public Backlog () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Integer getPTSeqquence () {
        return PTSeqquence;
    }

    public void setPTSeqquence (Integer PTSeqquence) {
        this.PTSeqquence = PTSeqquence;
    }

    public String getProjectIdentifier () {
        return projectIdentifier;
    }

    public void setProjectIdentifier (String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Project getProject () {
        return project;
    }

    public void setProject (Project project) {
        this.project = project;
    }
}
