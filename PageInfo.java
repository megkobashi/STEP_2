package browser_cache;

public class PageInfo {
	private PageInfo older;
	private PageInfo newer;
	private Object page;
	private String url;
	
	public PageInfo(String url, Object page) {
		this.page = page;
		this.url = url;
	}
	
	public void setOlder(PageInfo page_info) {
		this.older = page_info;
	}
	public void setNewer(PageInfo page_info) {
		this.newer = page_info;
	}
	public PageInfo getOlder() {
		return this.older;
	}
	public PageInfo getNewer() {
		return this.newer;
	}
	public Object getPage() {
		return this.page;
	}
	public String getURL() {
		return this.url;
	}
}