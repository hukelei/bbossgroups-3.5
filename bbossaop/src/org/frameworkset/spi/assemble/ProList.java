package org.frameworkset.spi.assemble;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * <p>Title: ProList.java</p> 
 * <p>Description: </p>
 * <p>bboss workgroup</p>
 * <p>Copyright (c) 2007</p>
 * @Date 2009-9-19 ����11:01:26
 * @author biaoping.yin
 * @version 1.0
 */
public  class ProList<V extends Pro> extends ArrayList<V>
{        
    private boolean isfreeze = false;
    /**
     * ����������ͣ��������������ͣ�
     * bean:������ֱ�ӽ���װ��ProListת��ΪList<po����>����
     * String��ProListת��ΪList<String>����
     * Pro��Ĭ������ProList<V extends Pro>������ת��������ָ��editor�༭��
     */
    private String componentType ;
    
    
    
    public void freeze()
    {
        this.isfreeze = true;
    }
    private boolean isFreeze()
    {
        
        return this.isfreeze;
    }
    
    private void modify() 
    {
        if(this.isFreeze())
            throw new CannotModifyException();
    }
    public int getInt(int i)
    {
        Pro value = this.get(i);
        if(value == null)
            return 0;
//        int value_ = Integer.parseInt(value.toString());
        return value.getInt();
    }
    @Override
    public void add(int index, V element)
    {
        modify() ;
        super.add(index, element);
    }
    @Override
    public boolean add(V o)
    {
        modify() ;
        return super.add(o);
    }
    @Override
    public boolean addAll(Collection<? extends V> c)
    {
        modify() ;
        return super.addAll(c);
    }
    @Override
    public boolean addAll(int index, Collection<? extends V> c)
    {
        modify() ;
        return super.addAll(index, c);
    }
    @Override
    public void clear()
    {
        modify() ;
        super.clear();
    }
    @Override
    public V remove(int index)
    {
        modify() ;
        return super.remove(index);
    }
    @Override
    public boolean remove(Object o)
    {
        modify() ;
        return super.remove(o);
    }
    @Override
    protected void removeRange(int fromIndex, int toIndex)
    {
        modify() ;
        super.removeRange(fromIndex, toIndex);
    }
    @Override
    public V set(int index, V element)
    {
        modify() ;
        return super.set(index, element);
    }
    @Override
    public boolean removeAll(Collection<?> c)
    {
        modify() ;
        return super.removeAll(c);
    }
    @Override
    public boolean retainAll(Collection<?> c)
    {
        modify() ;
        return super.retainAll(c);
    }
    public int getInt(int i,int defaultValue)
    {
        Pro value = this.get(i);
        if(value == null)
            return defaultValue;
//        int value_ = Integer.parseInt(value.toString());
        return value.getInt();
    }
    public Pro<?> getPro(int i)
    {
        return this.get(i);
    }
    
    
    
    
    public boolean getBoolean(int i)
    {
        Pro value = this.get(i);
        if(value == null)
            return false;
//        boolean value_ = Boolean.parseBoolean(value.toString());
        return value.getBoolean();
    }
    public boolean getBoolean(int i,boolean defaultValue)
    {
        Pro value = this.get(i);
        if(value == null)
            return defaultValue;
        boolean value_ = value.getBoolean(defaultValue);
        return value_;
    }
    
    public String getString(int i)
    {
        Pro value = this.get(i);
        if(value == null)
            return null;
        
        return value.getString();
    }
    public String getString(int i,String defaultValue)
    {
        Pro value = this.get(i);
        
        if(value == null)
            return defaultValue;
        
        return value.getString(defaultValue);
    }
    public ProList getList(int i,ProList defaultValue)
    {   
        Pro value = this.get(i);
        
        if(value == null)
            return defaultValue;
        
        return value.getList(defaultValue);
    }
    public ProList getList(int i)
    {   
        Pro value = this.get(i);
        if(value == null)
            return null;
        
        return value.getList();
    }
    
    public ProSet getSet(int i,ProSet defaultValue)
    {   
        Pro value = this.get(i);
        
        if(value == null)
            return defaultValue;
        
        return value.getSet(defaultValue);
    }
    public ProSet getSet(int i)
    {   
        Pro value = this.get(i);
        if(value == null)
            return null;
        
        return value.getSet();
    }
    
    public Object getObject(int i)
    {
        Pro value = this.get(i);
        if(value == null)
            return null;
        
        return value.getObject();
    }
    private List componentList;
    private Object lock = new Object();
    public List getComponentList(Class listtype)
    {
    	if(this.getComponentType() == null)
    		return this;
    	if(componentList == null)
    	{
    		synchronized(lock)
    		{
    			if(componentList == null)
    			{
//    				if(this.size() > 0)
    				{
    					if(this.componentType.equals(Pro.COMPONENT_BEAN))
    					{
//    						componentList = new ArrayList(this.size());
        					if(listtype != ArrayList.class)
    						{
        						try {
        							componentList = (ArrayList)listtype.newInstance();
    							} catch (InstantiationException e) {
    								throw new BeanInstanceException(e);
    							} catch (IllegalAccessException e) {
    								throw new BeanInstanceException(e);
    							}
    						}
    						else
    						{
    							componentList = new ArrayList(this.size());
    						}
	    					for(Pro pro:this)
	    					{
	    						componentList.add(pro.getBean());	
	    					}
    					}
    					else if(this.componentType
								.equalsIgnoreCase(Pro.COMPONENT_STRING_SHORTNAME) || this.componentType
								.equalsIgnoreCase(Pro.COMPONENT_STRING))
    					{
//    						componentList = new ArrayList(this.size());
    						if(listtype != ArrayList.class)
    						{
        						try {
        							componentList = (ArrayList)listtype.newInstance();
    							} catch (InstantiationException e) {
    								throw new BeanInstanceException(e);
    							} catch (IllegalAccessException e) {
    								throw new BeanInstanceException(e);
    							}
    						}
    						else
    						{
    							componentList = new ArrayList(this.size());
    						}
    						for(Pro pro:this)
	    					{
    							componentList.add(pro.getString());	
	    					}
    					}
    					else if(this.componentType.equalsIgnoreCase(Pro.COMPONENT_OBJECT_SHORTNAME) || this.componentType.equalsIgnoreCase(Pro.COMPONENT_OBJECT))
    					{
//    						componentList = new ArrayList(this.size());
    						if(listtype != ArrayList.class)
    						{
        						try {
        							componentList = (ArrayList)listtype.newInstance();
    							} catch (InstantiationException e) {
    								throw new BeanInstanceException(e);
    							} catch (IllegalAccessException e) {
    								throw new BeanInstanceException(e);
    							}
    						}
    						else
    						{
    							componentList = new ArrayList(this.size());
    						}
	    					for(Pro pro:this)
	    					{
	    						componentList.add(pro.getBean());	
	    					}
    					}
    					else
    					{
//    						componentList = new ArrayList(this.size());
    						if(listtype != ArrayList.class)
    						{
        						try {
        							componentList = (ArrayList)listtype.newInstance();
    							} catch (InstantiationException e) {
    								throw new BeanInstanceException(e);
    							} catch (IllegalAccessException e) {
    								throw new BeanInstanceException(e);
    							}
    						}
    						else
    						{
    							componentList = new ArrayList(this.size());
    						}
	    					for(Pro pro:this)
	    					{
	    						componentList.add(pro.getBean());	
	    					}
    					}
    				}
//    				else
//    				{
//    					componentList = new ArrayList(this.size());;
//    				}    				
    			}
    		}
    		
    	}
    	
    	return componentList;
    }
    
    public Object getObject(int i,Object defaultValue)
    {
        Pro value = this.get(i);
        
        if(value == null)
            return defaultValue;
        
        return value.getObject(defaultValue);
    }
    public Iterator<V> iterator() {
        return super.iterator();
    }
	/**
	 * @return the componentType
	 */
	public String getComponentType() {
		return componentType;
	}
	/**
	 * @param componentType the componentType to set
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
    
}