package com.example.iwasCap.repository;

import com.example.iwasCap.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Find all leave requests for an employee
    List<LeaveRequest> findByEmployeeId(Long employeeId);

    // Find all pending leave requests (for admin review)
    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.status = 'PENDING'")
    List<LeaveRequest> findAllPendingLeaveRequests();

    // Find leave requests by status (APPROVED, REJECTED, PENDING)
    List<LeaveRequest> findByStatus(String status);
}
