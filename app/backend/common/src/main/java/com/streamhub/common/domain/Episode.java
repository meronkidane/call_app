package com.streamhub.common.domain;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "episodes")
@CompoundIndex(name = "idx_episode_title_season", def = "{ 'titleId': 1, 'season': 1, 'number': 1 }")
public class Episode extends BaseDocument {

    private String titleId;

    private int season;

    private int number;

    private String name;

    private String synopsis;

    private int durationSec;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(int durationSec) {
        this.durationSec = durationSec;
    }
}
