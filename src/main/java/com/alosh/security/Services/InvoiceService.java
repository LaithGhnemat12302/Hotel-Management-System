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

    @Transactional
    public InvoiceResponse createInvoice(InvoiceRequest request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Invoice invoice = new Invoice();
        invoice.setAmount(request.getAmount());
        invoice.setReservation(reservation);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return new InvoiceResponse(savedInvoice.getId(), savedInvoice.getAmount(),
                savedInvoice.getReservation().getId(),
                savedInvoice.getReservation().getCustomer().getName(),
                savedInvoice.getReservation().getRoom().getType());
    }

    @Transactional
    public InvoiceResponse updateInvoice(Long invoiceId, InvoiceRequest request) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        invoice.setAmount(request.getAmount());
        invoice.setReservation(reservation);

        Invoice updatedInvoice = invoiceRepository.save(invoice);

        return new InvoiceResponse(updatedInvoice.getId(), updatedInvoice.getAmount(),
                updatedInvoice.getReservation().getId(),
                updatedInvoice.getReservation().getCustomer().getName(),
                updatedInvoice.getReservation().getRoom().getType());
    }

    public InvoiceResponse getInvoiceById(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        return new InvoiceResponse(invoice.getId(), invoice.getAmount(),
                invoice.getReservation().getId(),
                invoice.getReservation().getCustomer().getName(),
                invoice.getReservation().getRoom().getType());
    }

    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoice -> new InvoiceResponse(invoice.getId(), invoice.getAmount(),
                        invoice.getReservation().getId(),
                        invoice.getReservation().getCustomer().getName(),
                        invoice.getReservation().getRoom().getType()))
                        .collect(Collectors.toList());
    }

    @Transactional
    public void deleteInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        invoiceRepository.delete(invoice);
    }
}