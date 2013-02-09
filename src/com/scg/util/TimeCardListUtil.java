package com.scg.util;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.util.DateRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/27/13
 * Time: 7:57 AM
 */
public class TimeCardListUtil {

    /**
     * Get a list of TimeCards for the specified consultant.
     * @param timeCards - the list of time cards to extract the sub set from
     * @param consultant - the consultant whose TimeCards will be obtained.
     * @return a list of TimeCards for the specified consultant.
     */
    public static List<TimeCard> getTimeCardsForConsultant(List<TimeCard> timeCards, Consultant consultant) {
        List<TimeCard> consultantsTimeCards = new ArrayList<TimeCard>();
        for (TimeCard timeCard : timeCards) {
            if (timeCard.getConsultant().equals(consultant)) {
                consultantsTimeCards.add(timeCard);
            }
        }

        return consultantsTimeCards;
    }

    /**
     * Get a list of TimeCards that cover dates that fall within a date range. Each time may have time entries through
     * out one week beginning with the time card start date.
     * @param timeCards - the list of time cards to extract the sub set from
     * @param dateRange - The DateRange within which the dates of the returned TimeCards must fall.
     * @return a list of TimeCards having dates fall within the date range.
     */
    public static List<TimeCard> getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange) {
        List<TimeCard> cardsInRange = new ArrayList<TimeCard>();
        for (TimeCard timeCard : timeCards) {
            if (dateRange.isInRange(timeCard.getWeekStartingDay())) {
                cardsInRange.add(timeCard);
            }
        }

        return cardsInRange;
    }

    /**
     * Sorts this list into ascending order by consultant name..
     * @param timeCards - the list of time cards to sort
     */
    public static void sortByConsultantName(List<TimeCard> timeCards) {
        Collections.sort(timeCards, new TimeCardConsultantComparator());
    }

    /**
     * Sorts this list into ascending order, by the start date.
     * @param timeCards - the list of time cards to sort
     */
    public static void sortByStartDate(List<TimeCard> timeCards) {
        Collections.sort(timeCards, new TimeCardDateRangeComparator());
    }
}
