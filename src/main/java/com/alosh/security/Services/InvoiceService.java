package com.alosh.security.Services;

import com.alosh.security.Dto.InvoiceRequest;
import com.alosh.security.Dto.InvoiceResponse;
import com.alosh.security.Entity.Invoice;
import com.alosh.security.Entity.Reservation;
import com.alosh.security.Repositories.InvoiceRepository;
import com.alosh.security.Repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ReservationRepository reservationRepository;

    public InvoiceResponse createInvoice(InvoiceRequest request) {
        List<Reservation> reservations = reservationRepository.findAllById(request.getReservationIds());
        Invoice invoice = new Invoice();
        invoice.setAmount(request.getAmount());
        invoice.setReservations(reservations);

        for (Reservation reservation : reservations) {
            reservation.setInvoice(invoice);
        }

        Invoice savedInvoice = invoiceRepository.save(invoice);
        reservationRepository.saveAll(reservations);

        return convertToResponse(savedInvoice);
    }

    public InvoiceResponse updateInvoice(Long id, InvoiceRequest request) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));

        List<Reservation> reservations = reservationRepository.findAllById(request.getReservationIds());

        invoice.setAmount(request.getAmount());
        invoice.setReservations(reservations);

        for (Reservation reservation : reservations) {
            reservation.setInvoice(invoice);
        }

        Invoice updatedInvoice = invoiceRepository.save(invoice);
        reservationRepository.saveAll(reservations);

        return convertToResponse(updatedInvoice);
    }

    public InvoiceResponse getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));
        return convertToResponse(invoice);
    }

    public List<InvoiceResponse> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public void deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));
        invoice.getReservations().forEach(reservation -> reservation.setInvoice(null));
        reservationRepository.saveAll(invoice.getReservations());
        invoiceRepository.deleteById(id);
    }

    private InvoiceResponse convertToResponse(Invoice invoice) {
        List<Long> reservationIds = invoice.getReservations().stream()
                .map(Reservation::getId)
                .collect(Collectors.toList());
        return new InvoiceResponse(
                invoice.getId(),
                invoice.getAmount(),
                reservationIds
        );
    }
}