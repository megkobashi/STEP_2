package browser_cache;

import java.util.Map;

import org.junit.Test;

public class CacheTest {
	
	@Test
	public void repeatedHead() {
		Cache c = new Cache();
		c.add("facebook", new Object());
		c.add("yahoo", new Object());
		c.add("google", new Object());
		c.add("google", new Object());
		prettyPrint(c);
	}
	
	@Test
	public void repeatedTail() {
		Cache c = new Cache();
		c.add("facebook", new Object());
		c.add("google", new Object());
		c.add("facebook", new Object());
		prettyPrint(c);
	}
	
	@Test
	public void repeatedConsecutive() {
		Cache c = new Cache();
		c.add("tumblr", new Object());
		c.add("tumblr", new Object());
		prettyPrint(c);
	}
	
	@Test
	public void overCacheLimit() {
		Cache c = new Cache();
		c.add("facebook", new Object());
		c.add("yahoo", new Object());
		c.add("google", new Object());
		c.add("tumblr", new Object());
		c.add("quora", new Object());
	}
	
	@Test
	public static void prettyPrint(Cache c) {
		PageInfo target = c.cache.get(c.head);
		System.out.println(target.getURL());
		while (c.tail != target.getURL()) {
			System.out.println(target.getOlder().getURL());
			target = target.getOlder();
		}
		System.out.println();
	}
}
