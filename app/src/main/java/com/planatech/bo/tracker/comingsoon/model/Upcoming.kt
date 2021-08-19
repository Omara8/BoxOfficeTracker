package com.planatech.bo.tracker.comingsoon.model

data class Upcoming (
	val dates : Dates,
	val page : Int,
	val results : List<UpcomingResults>,
	val total_pages : Int,
	val total_results : Int
)