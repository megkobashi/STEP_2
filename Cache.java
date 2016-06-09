package browser_cache;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	static int CACHE_SIZE = 6; // including dummy_head and dummy_tail
	public static Map<String, PageInfo> cache;

	public static String tail = null;
	public static String head = null;

	public Cache {
		cache = new HashMap<String, PageInfo>(CACHE_SIZE);
		PageInfo dummy_head = new PageInfo("dummy_head", new Object());
		cache.put("dummy_head", dummy_head);
		PageInfo dummy_tail = new PageInfo("dummy_tail", new Object());
		dummy_tail.setNewer(dummy_head);
		head = "dummy_head";
		tail = "dummy_tail";
		cache.put("dummy_tail", dummy_tail);
		dummy_head.setOlder(dummy_tail);
	}

	public void add(String url, Object page) {
		PageInfo page_info = new PageInfo(url, page);
		if (cache.keySet().size() ==  2) {
			insert(url, page_info, head, tail);
			tail = url;
		} else if (cache.keySet().size() == 3) {
			if (!search(url)) {
				insert(url, page_info, head, tail);
				head = url;
			}
		}  else { // i.e. if head and tail are already defined
			if (head.equals(url)) {
				return;
			} else if (cache.keySet().size() == CACHE_SIZE || tail.equals(url)) { // delete from tail
				String newTail = cache.get(tail).getNewer().getURL();
				delete(tail);
				tail = newTail;
				insert(url, page_info, "dummy_head", head);
				head = url;
			} else {
				if (search(url)) {
					delete(url);
				}
				insert(url, page_info, "dummy_head", head);
				head = url;
			}
		} 
	}
	
	// create 2 way relationship (a = b and b = a) with its neighbors
	public void insert(String url, PageInfo page_info, String before, String after) {
		cache.put(url, page_info);
		cache.get(after).setNewer(page_info);
		page_info.setOlder(cache.get(after));
		cache.get(before).setOlder(page_info);
		page_info.setNewer(cache.get(before));
	}

	public void delete(String key) {
		PageInfo target = cache.get(key);
		target.getNewer().setOlder(target.getOlder());
		target.getOlder().setNewer(target.getNewer());
		cache.remove(key);
	}

	public boolean search(String key) {
		return cache.keySet().contains(key);
	}
}
