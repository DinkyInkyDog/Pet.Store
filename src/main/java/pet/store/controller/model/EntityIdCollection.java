package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EntityIdCollection {
	private Long id1;
	private Long id2;
	private Long id3;
	
	public EntityIdCollection(Long id1, Long id2) {
		this.id1 = id1;
		this.id2 = id2;
	}
	
	public EntityIdCollection(Long id1) {
		this.id1 = id1;
	}
}
