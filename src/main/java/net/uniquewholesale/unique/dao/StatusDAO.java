package net.uniquewholesale.unique.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.uniquewholesale.unique.catalog.Status;

public interface StatusDAO extends JpaRepository<Status, Long>{

}
