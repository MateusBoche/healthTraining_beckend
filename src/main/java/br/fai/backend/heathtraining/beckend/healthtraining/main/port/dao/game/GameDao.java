package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.CreateDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.CrudDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.DeleteDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.ReadDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game.ReadGamesByEmail;

import java.util.List;

public interface GameDao extends CreateDao<GameModel>, DeleteDao, ReadDao<GameModel>, CrudDao<GameModel>,UpdatePointsDao,GetBestUserPoints, ReadGamesByEmail {

}
