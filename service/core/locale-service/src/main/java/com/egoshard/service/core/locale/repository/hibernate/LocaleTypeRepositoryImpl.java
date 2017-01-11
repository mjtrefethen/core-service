package com.egoshard.service.core.locale.repository.hibernate;

import com.egoshard.service.core.locale.domain.LocaleType;
import com.egoshard.service.core.locale.repository.LocaleTypeRepository;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class LocaleTypeRepositoryImpl implements LocaleTypeRepository {

  private final EntityManager entityManager;

  /**
   *
   * @param entityManager
   */
  @Autowired
  public LocaleTypeRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * Method to retrieve a list of all LocaleTypes.
   *
   * @param includeInactive boolean value indicating that returned data should include inactive
   * models.
   * @return a List of LocaleType objects meeting the specified criteria.
   */
  @Override
  public List<LocaleType> findAll(boolean includeInactive) {
    String HQL = includeInactive ? "SELECT lt FROM LocaleType lt" : "SELECT lt FROM LocaleType lt WHERE c.isActive = true";
    return entityManager.createQuery(HQL, LocaleType.class).getResultList();
  }

  /**
   * Method to retrieve a specific LocalType model by unique identifier.
   *
   * @param key unique model identifier
   * @return populated LocaleType object
   */
  @Override
  public LocaleType findByKey(String key) {
    return null;
  }

  /**
   * Method to create or update an existing LocaleType model.
   *
   * @param localeType model object to save
   */
  @Override
  public String saveModel(LocaleType localeType) {
    return "";
  }

  /**
   * Method to deactivate an existing LocaleType model.
   *
   * @param key unique model identifier
   */
  @Override
  public void deleteModel(String key) {

  }

}
