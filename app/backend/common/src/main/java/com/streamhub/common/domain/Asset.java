package com.streamhub.common.domain;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "assets")
public class Asset extends BaseDocument {

    @Indexed
    private String parentId;

    private List<String> codecs;

    private List<String> renditions;

    private String drm;

    private String hlsUrl;

    private String dashUrl;

    private List<String> captions;

    private List<String> posters;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getCodecs() {
        return codecs;
    }

    public void setCodecs(List<String> codecs) {
        this.codecs = codecs;
    }

    public List<String> getRenditions() {
        return renditions;
    }

    public void setRenditions(List<String> renditions) {
        this.renditions = renditions;
    }

    public String getDrm() {
        return drm;
    }

    public void setDrm(String drm) {
        this.drm = drm;
    }

    public String getHlsUrl() {
        return hlsUrl;
    }

    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

    public String getDashUrl() {
        return dashUrl;
    }

    public void setDashUrl(String dashUrl) {
        this.dashUrl = dashUrl;
    }

    public List<String> getCaptions() {
        return captions;
    }

    public void setCaptions(List<String> captions) {
        this.captions = captions;
    }

    public List<String> getPosters() {
        return posters;
    }

    public void setPosters(List<String> posters) {
        this.posters = posters;
    }
}
