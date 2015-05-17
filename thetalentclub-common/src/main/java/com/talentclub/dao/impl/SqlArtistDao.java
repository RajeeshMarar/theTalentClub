package com.talentclub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.talentclub.dao.ArtistDao;
import com.talentclub.domain.ArtistLocation;
import com.talentclub.domain.SkillSet;

public class SqlArtistDao extends HibernateDaoSupport implements ArtistDao {

	@SuppressWarnings("unchecked")
	public List<SkillSet> getSkillSet() {
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		List<SkillSet> skillSetList = new ArrayList<SkillSet>();
		try {
			session.beginTransaction();
			Query hqlQuery = session.createQuery("from SkillSet");
			
			skillSetList.addAll(hqlQuery.list());
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
		}
		return skillSetList;
	}

	public List<ArtistLocation> getAllLocations() {
		List<ArtistLocation> artLocationLst= new ArrayList<ArtistLocation>();
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		try {
			//log.debug("creating session to fetch session for the query");
			session.beginTransaction();
			Query hqlQuery = session.createSQLQuery("select location_name from location");//SQuery("select distinct al.locationId , al.locationName  from ArtistLocation as al");
			@SuppressWarnings("unchecked")
			List<String> locations= hqlQuery.list();
			for(String location :locations) {
				artLocationLst.add(new ArtistLocation(location, location));
				//log.debug("loc id "+location+" loc name "+location);
			}
			session.getTransaction().commit();
	
			
		} catch(Exception e) {
			//log.error("some error in fetching locations", e);
			session.getTransaction().rollback();
		}
		return artLocationLst;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchArtist(String locationId, String skillSet, int budgetLower, 
											int budgetUpper) {

		List<Object[]> result = new ArrayList<Object[]>();
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
	
			String query = "select a.artist_id,a.first_name,a.last_name,a.stage_name,a.gender,a.skill_set,"
					+ " a.quote,a.description,a.email,a.phone_no,a.address,a.profile_photo_id,"
					+ " a.status,a.creation_date, a.update_date,a.featured,a.fee_lower,a.fee_upper,a.commission,"
					+ " al.location_id,al.location_name, ap.photo_id, ap.photo_uri"
					+ " from artist a left join artist_location al on a.artist_id = al.artist_id"
					+ " left join artist_photos ap on a.profile_photo_id = ap.photo_id"
					+ " where 1=1 ";
			
					if(!"".equals(locationId)) {
						query += " and ( al.location_id = ? or al.location_id = 'ALL' )";
					} 
					if(!"".equals(skillSet)) {
						query += " and a.skill_set like ?";
					}
					if(budgetLower!= -1 && budgetUpper!=-1){
						query += " and a.fee_lower <= ? and a.fee_upper >= ?";
					}
					query+=" order by artist_id";

			SQLQuery sqlQuery=session.createSQLQuery(query);
			
			int counter = 0;
			if(!"".equals(locationId)) {
				sqlQuery.setString(counter++,  locationId);
			} 
			if(!"".equals(skillSet)) {
				sqlQuery.setString(counter++,  "%"+skillSet+"%");
			}
			
			if(budgetLower!= -1 && budgetUpper!=-1) {
				sqlQuery.setInteger(counter++,  budgetUpper);
				sqlQuery.setInteger(counter,  budgetLower);
			}
			result = sqlQuery.list();
		
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
		}
		return result;

	}
}
