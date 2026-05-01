package com.mw.freemarker.collections.contributor;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.TemplateContextContributor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    immediate = true,
	    property = {"type=" + TemplateContextContributor.TYPE_GLOBAL},
	    service = TemplateContextContributor.class
)
public class FreemarkerCollectionsContributor implements TemplateContextContributor {
	
	@Activate
	protected void activate(Map<String, Object> properties)  throws Exception {
		_log.info("activated");
	}

    @Override
    public void prepare(
        Map<String, Object> contextObjects,
        HttpServletRequest httpServletRequest) {

        contextObjects.put("assetEntryLocalService", _assetEntryLocalService);
        contextObjects.put("journalArticleLocalService", _journalArticleLocalService);
        
        contextObjects.put("journalArticleAssetEntryHelper", _journalArticleAssetEntryHelper);
    }
    
    @Reference
	private JournalArticleAssetEntryHelper _journalArticleAssetEntryHelper;    
    
    @Reference
	private AssetEntryLocalService _assetEntryLocalService;   
    
    @Reference
	private JournalArticleLocalService _journalArticleLocalService;        
    
    private static final Log _log = LogFactoryUtil.getLog(FreemarkerCollectionsContributor.class);
}