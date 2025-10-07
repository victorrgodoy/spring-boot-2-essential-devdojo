package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.requests.AnimePostRequestBody;
import academy.devdojo.springboot2.requests.AnimePutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final AnimeMapper animeMapper;

    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public Anime findByIdOrThrowBadRequestException(Long id){
        return animeRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("Anime Not Foud"));
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(animeMapper.toAnime(animePostRequestBody));
    }

    public void delete(Long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = animeRepository.save(animeMapper.toAnime(animePutRequestBody));
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }
}
