# Sample Media Assets

Place public-domain video segments and artwork in this directory to exercise the local encoding pipeline.

Recommended structure:

```
media/sample/
  movie/
    master.m3u8
    master.mpd
    captions_en.vtt
    poster.jpg
    artwork.jpg
  series/
    master.m3u8
    master.mpd
    poster.jpg
  shared/
    trailers/
```

The `scripts/seed_dev_data.sh` script will mirror this directory into the MinIO bucket for development.
