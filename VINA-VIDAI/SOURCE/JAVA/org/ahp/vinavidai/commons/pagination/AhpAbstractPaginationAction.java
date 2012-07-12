/*
 * Copyright 2012 Anita Onnuvel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ahp.vinavidai.commons.pagination;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.form.AhpPageRange;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.PaginationOperations;
import org.ahp.vinavidai.enums.SubmitActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public abstract class AhpAbstractPaginationAction extends Action {

    final static Logger LOGGER = LoggerFactory
            .getLogger( AhpAbstractPaginationAction.class );

    @Override
    public ActionForward execute( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        AhpAbstractPaginationForm lAhpAbstractPaginationForm = ( AhpAbstractPaginationForm ) pActionForm;
        String lAction = StringUtils.trimToEmpty( lAhpAbstractPaginationForm
                .getSubmitAction() );
        User lLoggedInUser = AhpActionHelper
                .getLoggedInUser( pHttpServletRequest );
        if ( "".equals( lAction )
                && "".equals( StringUtils
                        .trimToEmpty( lAhpAbstractPaginationForm
                                .getPaginationData().getOperation() ) ) ) {
            populatePaginationSearchCriteria( lAhpAbstractPaginationForm,
                    lLoggedInUser, true );
        }
        // User clicked Go Button with Order By.
        else if ( StringUtils.equals( SubmitActions.GO.toString(), lAction ) ) {
            if ( LOGGER.isDebugEnabled() )
                LOGGER.debug( "User Pressed Go Button" );
            lAhpAbstractPaginationForm.getPaginationData().setStartIndex( 1 );
            lAhpAbstractPaginationForm.getPaginationData().setEndIndex( 25 );
            lAhpAbstractPaginationForm.getPaginationData().setSelectedPage( 1 );
            populatePaginationSearchCriteria( lAhpAbstractPaginationForm,
                    lLoggedInUser, false );
        }
        // User selects a Page Range from the ComboBox. et Start and End Indexes
        // based on the HiddenComboValue (Page Number he is requesting).
        else if ( StringUtils.equals( PaginationOperations.ComboSubmit
                .toString(), StringUtils
                .trimToEmpty( lAhpAbstractPaginationForm.getPaginationData()
                        .getOperation() ) ) ) {
            if ( LOGGER.isDebugEnabled() )
                LOGGER.debug( "User Selected a Page Range from the ComboBox" );
            int lSelectedComboValue = lAhpAbstractPaginationForm
                    .getPaginationData().getHiddenSelectedComboValue();
            lAhpAbstractPaginationForm.getPaginationData().setEndIndex(
                    lSelectedComboValue
                            * lAhpAbstractPaginationForm.getPaginationData()
                                    .getMaxRecordsPerPage() );
            lAhpAbstractPaginationForm.getPaginationData().setStartIndex(
                    lAhpAbstractPaginationForm.getPaginationData()
                            .getEndIndex()
                            - lAhpAbstractPaginationForm.getPaginationData()
                                    .getMaxRecordsPerPage() + 1 );
            lAhpAbstractPaginationForm.getPaginationData().setSelectedPage(
                    lSelectedComboValue );
            populatePaginationSearchCriteria( lAhpAbstractPaginationForm,
                    lLoggedInUser, false );
        }
        // User Clicks Previous HyperLink. In this action the user might have
        // the search filters still on. Look for them and populate
        else if ( StringUtils.equals( PaginationOperations.ShowPrevious
                .toString(), StringUtils
                .trimToEmpty( lAhpAbstractPaginationForm.getPaginationData()
                        .getOperation() ) ) ) {
            if ( LOGGER.isDebugEnabled() )
                LOGGER.debug( "User Clicked Previous" );
            populatePaginationSearchCriteria( lAhpAbstractPaginationForm,
                    lLoggedInUser, false );
            loadPageIndexes( lAhpAbstractPaginationForm );
        }
        // User Clicks Next HyperLink. In this action the user might have the
        // search filters still on. Look for them and populate
        else if ( StringUtils.equals( PaginationOperations.ShowNext.toString(),
                StringUtils.trimToEmpty( lAhpAbstractPaginationForm
                        .getPaginationData().getOperation() ) ) ) {
            if ( LOGGER.isDebugEnabled() )
                LOGGER.debug( "User Clicked Next" );
            populatePaginationSearchCriteria( lAhpAbstractPaginationForm,
                    lLoggedInUser, false );
            loadPageIndexes( lAhpAbstractPaginationForm );
        }
        ActionForward lActionForward = paginate( pActionMapping, pActionForm,
                pHttpServletRequest, pHttpServletResponse );
        lAhpAbstractPaginationForm.setSubmitAction( null );
        // this.clearPagination( lAhpAbstractPaginationForm );
        return lActionForward;
    }

    public abstract ActionForward paginate( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse );

    public abstract void populatePaginationSearchCriteria(
            AhpAbstractPaginationForm pAhpAbstractPaginationForm,
            User pLoggedInUser, boolean pDefault );

    /**
     * 
     * @param pAhpAbstractPaginationForm
     */
    protected void loadPageIndexes(
            AhpAbstractPaginationForm pAhpAbstractPaginationForm ) {
        int lPreviousComboPagination = pAhpAbstractPaginationForm
                .getPaginationData().getSelectedPage();
        // showPrevious
        if ( StringUtils.equalsIgnoreCase(
                PaginationOperations.ShowPrevious.toString(),
                pAhpAbstractPaginationForm.getPaginationData().getOperation() ) ) {
            if ( lPreviousComboPagination != 0 ) {
                int lComboPgn = new Integer( lPreviousComboPagination )
                        .intValue() - 1;
                pAhpAbstractPaginationForm.getPaginationData().setSelectedPage(
                        lComboPgn );
                pAhpAbstractPaginationForm.getPaginationData().setEndIndex(
                        lComboPgn
                                * pAhpAbstractPaginationForm
                                        .getPaginationData()
                                        .getMaxRecordsPerPage() );
                pAhpAbstractPaginationForm.getPaginationData().setStartIndex(
                        pAhpAbstractPaginationForm.getPaginationData()
                                .getEndIndex()
                                - pAhpAbstractPaginationForm
                                        .getPaginationData()
                                        .getMaxRecordsPerPage() + 1 );
            }
        } else if ( StringUtils.equalsIgnoreCase(
                PaginationOperations.ShowNext.toString(),
                pAhpAbstractPaginationForm.getPaginationData().getOperation() ) ) {
            if ( lPreviousComboPagination != 0 ) {
                int lComboPgn = new Integer( lPreviousComboPagination )
                        .intValue() + 1;
                pAhpAbstractPaginationForm.getPaginationData().setSelectedPage(
                        lComboPgn );
                pAhpAbstractPaginationForm.getPaginationData().setEndIndex(
                        lComboPgn
                                * pAhpAbstractPaginationForm
                                        .getPaginationData()
                                        .getMaxRecordsPerPage() );
                pAhpAbstractPaginationForm.getPaginationData().setStartIndex(
                        pAhpAbstractPaginationForm.getPaginationData()
                                .getEndIndex()
                                - pAhpAbstractPaginationForm
                                        .getPaginationData()
                                        .getMaxRecordsPerPage() + 1 );
            }
        }// showNext
        pAhpAbstractPaginationForm.getPaginationSearchCriteria().setStartIndex(
                pAhpAbstractPaginationForm.getPaginationData().getStartIndex() );
        pAhpAbstractPaginationForm.getPaginationSearchCriteria().setEndIndex(
                pAhpAbstractPaginationForm.getPaginationData().getEndIndex() );
    }

    /**
     * 
     * @param pAhpAbstractPaginationForm
     * @param pStartIndex
     * @param pEndIndex
     */
    protected void loadPageFlags(
            AhpAbstractPaginationForm pAhpAbstractPaginationForm ) {
        int lTotalRecords = new Integer( pAhpAbstractPaginationForm
                .getPaginationData().getTotalRecords() ).intValue();
        if ( pAhpAbstractPaginationForm.getPaginationData().getEndIndex() >= lTotalRecords ) {
            pAhpAbstractPaginationForm.getPaginationData().setShowNextPageFlag(
                    false );
        } else if ( pAhpAbstractPaginationForm.getPaginationData()
                .getEndIndex() <= lTotalRecords ) {
            pAhpAbstractPaginationForm.getPaginationData().setShowNextPageFlag(
                    true );
        }
        if ( pAhpAbstractPaginationForm.getPaginationData().getStartIndex() <= 1 ) {
            pAhpAbstractPaginationForm.getPaginationData()
                    .setShowPreviousPageFlag( false );
        } else if ( pAhpAbstractPaginationForm.getPaginationData()
                .getStartIndex() > 1 ) {
            pAhpAbstractPaginationForm.getPaginationData()
                    .setShowPreviousPageFlag( true );
        }
    }

    protected List<AhpPageRange> loadComboPageRange(
            AhpAbstractPaginationForm pAhpAbstractPaginationForm ) {
        List<String> lPageRangeList = generatePageRangeList(
                pAhpAbstractPaginationForm.getPaginationData()
                        .getTotalRecords(), pAhpAbstractPaginationForm
                        .getPaginationData().getMaxRecordsPerPage() );
        List<AhpPageRange> lComboPageRangeList = new ArrayList<AhpPageRange>();
        int lPageCount = 1;
        for ( String lPageRange : lPageRangeList ) {
            lComboPageRangeList.add( new AhpPageRange( new Integer( lPageCount )
                    .toString(), lPageRange ) );
            lPageCount++;
        }
        return lComboPageRangeList;
    }

    /**
     * 
     * @param pMaxSize
     * @param pPageSize
     * @return
     */
    public static List<String> generatePageRangeList( Integer pMaxSize,
            Integer pPageSize ) {
        List<String> lPageRangeList = new ArrayList<String>();
        if ( pMaxSize > 0 ) {
            for ( int i = 1; i <= pMaxSize; i++ ) {
                if ( i % pPageSize == 0 ) {
                    lPageRangeList.add( i - pPageSize + 1 + "-" + i );
                } else if ( i == pMaxSize ) {
                    lPageRangeList.add( pMaxSize - ( pMaxSize % pPageSize ) + 1
                            + "-" + pMaxSize );
                }
            }
        }
        return lPageRangeList;
    }

    /**
     * 
     * @param pAbstractForm
     */
    public void clearPagination(
            AhpAbstractPaginationForm pAhpAbstractPaginationForm ) {
        if ( LOGGER.isDebugEnabled() )
            LOGGER.debug( "clearPagination :: starts" );
        pAhpAbstractPaginationForm.setPaginationData( null );
        if ( LOGGER.isDebugEnabled() )
            LOGGER.debug( "clearPagination :: ends" );
    }

    /**
     * 
     * @param pAbstractForm
     */
    public void clearSubmitAction(
            AhpAbstractPaginationForm pAhpAbstractPaginationForm ) {
        if ( LOGGER.isDebugEnabled() )
            LOGGER.debug( "clearSubmitAction :: starts" );
        pAhpAbstractPaginationForm.setSubmitAction( null );
        if ( LOGGER.isDebugEnabled() )
            LOGGER.debug( "clearSubmitAction :: ends" );
    }
}
