package com.trendyol.shoppingcart.domain.base;


//Simple usage of template pattern
public abstract class TitleVO {
	
	private String val;
	
	public String getVal() {
		return val;
	}

	protected TitleVO(String val) {
		validate(val);
		this.val = val;
	}

	protected abstract void validate(String val2);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((val == null) ? 0 : val.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TitleVO other = (TitleVO) obj;
		if (val == null) {
			if (other.val != null)
				return false;
		} else if (!val.equals(other.val))
			return false;
		return true;
	}
	
}
