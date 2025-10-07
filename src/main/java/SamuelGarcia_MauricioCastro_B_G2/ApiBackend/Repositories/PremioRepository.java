package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Repositories;

import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Entities.PremiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PremioRepository extends JpaRepository<PremiosEntity, Long> {
}
