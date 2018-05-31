package com.cts.cde.io.cookbook.platform;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PlatformServiceImpl implements PlatformService {

	 @Autowired
	    PlatformRepository platformRepository;
	 
	 @Override
		public List<Platform> findAll() {
			List<Platform> platforms = new ArrayList();
			 platformRepository.findAll().forEach(platforms::add);
			 return platforms;
		}

		@Override
		public Platform findById(long id) {
			
			return platformRepository.findOne(id);
		}

	 
		@Override
		public Platform createPlatform(Platform platform) {
			
			return platformRepository.save(platform);
		}
	
		@Override
		public Platform updatePlatform(Platform platform, long id) {
			
			return platformRepository.save(platform);
		}
	
		@Override
		public void deletePlatform(long id) {
			
			platformRepository.delete(id);
		}


	
	

}
