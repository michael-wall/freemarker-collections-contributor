package com.mw.freemarker.collections.contributor;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		service = JournalArticleAssetEntryHelper.class)
public class JournalArticleAssetEntryHelper {
	
	@Activate
	protected void activate(Map<String, Object> properties)  throws Exception {
		_log.info("activated");
	}

	public JournalArticle getJouralArticleByAssetEntryId(long assetEntryId) {
		if (assetEntryId <= 0) return null;
				
		AssetEntry assetEntry = _assetEntryLocalService.fetchAssetEntry(assetEntryId);
		
		if (assetEntry == null) return null;
		if (!assetEntry.getClassName().equalsIgnoreCase(JournalArticle.class.getCanonicalName())) return null;
		
		JournalArticle journalArticle = _journalArticleLocalService.fetchLatestArticle(assetEntry.getClassPK());
		
		return journalArticle;
	}
    
    @Reference
	private AssetEntryLocalService _assetEntryLocalService;   
    
    @Reference
	private JournalArticleLocalService _journalArticleLocalService;        
    
    private static final Log _log = LogFactoryUtil.getLog(JournalArticleAssetEntryHelper.class);
}