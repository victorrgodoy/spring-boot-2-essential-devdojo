package academy.devdojo.springboot2.mapper;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimePostRequestBody;
import academy.devdojo.springboot2.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimeMapper {
    Anime toAnime(AnimePostRequestBody animePostRequestBody);
    Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
