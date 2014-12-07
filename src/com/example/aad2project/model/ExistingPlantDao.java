package com.example.aad2project.model;


import android.content.Context;
import android.util.Log;

import com.example.aad2project.object.ExistingPlant;


public class ExistingPlantDao extends DaoBase{

	
	private Context context;

	public ExistingPlantDao(Context pContext){
		super(pContext);
		super.open();
		this.context = pContext;
	}

	@Override
	protected boolean convertResultToObject() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * This method adds a new Existing plant to the database
	 * @param p
	 */
	public void addExistingPlant(ExistingPlant p){
		mDb.execSQL("INSERT INTO existingPlants " +
				"(id,name,description,timetogrow,weatherid)" +
				"VALUES" +
				"('"+p.getPlantId()+"','"+p.getName()+"','"+p.getDescription()+"',"+p.getTimeToGrow()+","+p.getWeatherId()+")");
			Log.d("EXISTINGPLANT ADD", p.getPlantId()+" ");
	}
	
	/**
	 * Returns a ExistingPlant from the database where Plantid = id
	 * @param id
	 * @return
	 */
	public ExistingPlant getPlant(final int id) {
		//p=null;
		/*tableEp.execute(new TableQueryCallback<ExistingPlant>() {
            @Override
			public void onCompleted(List<ExistingPlant> result, int count,
                    Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        for (ExistingPlant item : result) {
                            if(item.getPlantId() == id){
                            	p.setDescription(item.getDescription());
                        		p.setName(item.getName());
                        		p.setExistingId(item.getPlantId());
                        		p.setTimeToGrow(item.getTimeToGrow());
                        		p.setWeatherId(item.getWeatherId());
                        		
                            }
                        }
                        
                    }
                }
            });
		return p;*/
		
		ExistingPlant p = new ExistingPlant();
		c = super.mDb.rawQuery("SELECT * FROM existingPlants " + "WHERE id = "
				+ id, null);
		if (c.moveToFirst()) {
			p.setPlantId(id);
			p.setName(c.getString(1));
			p.setDescription(c.getString(2));
			p.setTimeToGrow(c.getInt(3));
			p.setWeatherId(c.getInt(4));
			return p;
		} else {
			return null;
		}
	}
	
}
